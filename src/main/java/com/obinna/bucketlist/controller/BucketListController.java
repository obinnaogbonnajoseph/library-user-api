package com.obinna.bucketlist.controller;

import com.obinna.bucketlist.dto.BucketListDto;
import com.obinna.bucketlist.dto.BucketListItemDto;
import com.obinna.bucketlist.model.BucketList;
import com.obinna.bucketlist.model.BucketListItem;
import com.obinna.bucketlist.repository.BucketListItemRepository;
import com.obinna.bucketlist.repository.BucketListRepository;
import com.obinna.bucketlist.service.BucketListItemService;
import com.obinna.bucketlist.service.BucketListService;
import com.obinna.bucketlist.utils.NotCreatedException;
import com.obinna.bucketlist.utils.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Api(value = "Bucket List API")
@RestController
@RequestMapping("bucketlists")
public class BucketListController {

    @Autowired
    BucketListService bucketListService;

    @Autowired
    BucketListItemService itemService;

    @Autowired
    BucketListRepository bucketListRepository;

    @Autowired
    BucketListItemRepository itemRepository;

    @ApiOperation(value = "create bucket list", response = BucketList.class)
    @PostMapping()
    public ResponseEntity<BucketList> createBucketList( @ApiParam(value = "bucket list body", required = true)
                                                            @NotNull @Valid @RequestBody BucketListDto dto) throws
            NotCreatedException, ResourceNotFoundException {
        return ResponseEntity.ok(bucketListService.createBucketList(dto));
    }

    @ApiOperation(value = "fetch bucket lists", response = List.class)
    @GetMapping()
    public ResponseEntity<List<BucketList>> getBucketLists(@ApiParam(value = "bucket list name")
                                                               @RequestParam("q") Optional<String> searchItem,
                                                           @ApiParam(value = "page") @RequestParam("page") Optional<Integer> page,
                                                           @ApiParam(value = "limit") @RequestParam("limit") Optional<Integer> limit) {

        List<BucketList> bucketLists;
        int limitFetch = limit.map(integer -> (integer > 100 ? 100 : integer)).orElse(20); // place a minimum restriction of 20, max restriction of 100
        Pageable pagination = PageRequest.of(page.orElse(0), limitFetch,
                Sort.Direction.ASC, "name");
        if(searchItem.isPresent()) {
            bucketLists = bucketListRepository.findAllByNameContaining(searchItem.get(), pagination);
        } else {
            bucketLists = bucketListRepository.findAll(pagination).getContent();
        }
        return ResponseEntity.ok(bucketLists);
    }

    @ApiOperation(value = "fetch single bucket list", response = BucketList.class)
    @GetMapping("{id}")
    public ResponseEntity<BucketList> getBucketList(@ApiParam(value = "bucket list id", required = true)
                                                        @PathVariable("id") Long bucketListId) throws ResourceNotFoundException {
        return ResponseEntity.ok(bucketListRepository.findById(bucketListId)
                .orElseThrow(() -> new ResourceNotFoundException("Bucket List does not exist")));
    }

    @ApiOperation(value = "update bucket list", response = BucketList.class)
    @PutMapping("{id}")
    public ResponseEntity<BucketList> updateBucketList(@ApiParam(value = "bucket list id", required = true)
                                                           @PathVariable("id") Long bucketListId,
                                                       @ApiParam(value = "bucket list request body", required = true)
                                                            @Valid @RequestBody BucketListDto dto) throws ResourceNotFoundException {
        dto.setId(bucketListId);
        return ResponseEntity.ok(bucketListService.updateBucketList(dto));
    }

    @ApiOperation(value = "delete bucket list", response = String.class)
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBucketList(@ApiParam(value = "bucket list id", required = true)
                                                  @PathVariable("id") Long bucketListId) throws ResourceNotFoundException {
        bucketListService.deleteBucketList(bucketListId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Bucket List with id %d deleted", bucketListId));
    }

    @ApiOperation(value = "create bucket list item", response = BucketListItem.class)
    @PostMapping("{id}/items")
    public ResponseEntity<BucketListItem> createBucketListItem(@ApiParam(value = "bucket list id", required = true)
                                                                   @PathVariable("id") Long bucketListId,
                                                               @ApiParam(value = "bucket list item request body", required = true)
                                                               @NotNull @Valid @RequestBody BucketListItemDto dto)
            throws NotCreatedException {
        return ResponseEntity.ok(itemService.createItem(dto));
    }

    @ApiOperation(value = "fetch bucket list items assigned to a bucket list", response = List.class)
    @GetMapping("{id}/items")
    public ResponseEntity<List<BucketListItem>> getBucketListItems(@ApiParam(value = "bucket list id", required = true)
                                                                       @PathVariable("id") Long bucketListId,
                                                @ApiParam(value = "page") @RequestParam("page") Optional<Integer> page,
                                                @ApiParam(value = "limit") @RequestParam("limit") Optional<Integer> limit) {
        List<BucketListItem> bucketItems;
        int limitFetch = limit.map(integer -> (integer > 100 ? 100 : integer)).orElse(20); // place a minimum restriction of 20, max restriction of 100
        Pageable pagination = PageRequest.of(page.orElse(0), limitFetch,
                Sort.Direction.ASC, "name");
        bucketItems = itemRepository.findAll(pagination).getContent();
        return ResponseEntity.ok(bucketItems);
    }

    @ApiOperation(value = "fetch single bucket list item", response = BucketListItem.class)
    @GetMapping("{id}/items/{bucketItemId}")
    public ResponseEntity<BucketListItem> getBucketListItem(@ApiParam(value = "bucket list id", required = true)
                                                                @PathVariable("id") Long bucketListId,
                                               @ApiParam(value = "bucket list item id", required = true)
                                                        @PathVariable("bucketItemId") Long bucketListItemId) throws ResourceNotFoundException {
        return ResponseEntity.ok(itemRepository.findById(bucketListItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Bucket Item does not exist")));
    }

    @ApiOperation(value = "update bucket list item", response = BucketListItem.class)
    @PutMapping("{id}/items/{bucketItemId}")
    public ResponseEntity<BucketListItem> updateBucketListItem(@ApiParam(value = "bucket list id", required = true)
                                                                   @PathVariable("id") Long bucketListId,
                                              @ApiParam(value = "bucket item id", required = true)
                                                    @PathVariable("bucketItemId") Long bucketItemId,
                                              @ApiParam(value = "bucket item request body", required = true)
                                                                   @Valid @RequestBody BucketListItemDto dto) throws ResourceNotFoundException {
        dto.setId(bucketItemId);
        return ResponseEntity.ok(itemService.updateItem(dto));
    }

    @ApiOperation(value = "delete bucket list item", response = String.class)
    @DeleteMapping("{id}/items/{bucketItemId}")
    public ResponseEntity<?> deleteBucketList(@ApiParam(value = "bucket list id", required = true)
                                                  @PathVariable("id") Long bucketListId,
                                              @ApiParam(value = "bucket item id", required = true)
                                                @PathVariable("bucketItemId") Long bucketItemId) throws ResourceNotFoundException {
        itemService.deleteItem(bucketItemId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format("Bucket item of id %d has been deleted", bucketItemId));
    }
}
