package br.com.fiap.environment.alert.controller;

import br.com.fiap.environment.alert.dto.AddIndexInPut;
import br.com.fiap.environment.alert.dto.GetIndexOutPut;
import br.com.fiap.environment.alert.service.IndexService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/index")
public class IndexController {

    IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping
    public ResponseEntity addIndex(@RequestBody final AddIndexInPut index) {
        indexService.add(index);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateIndex(@RequestBody final AddIndexInPut index) {
        indexService.update(index);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity get(@PageableDefault(size = 3, page = 1) Pageable pagination) {
        Page<GetIndexOutPut> indexes = indexService.getIndex(pagination);
        return ResponseEntity.ok(indexes);
    }

}
