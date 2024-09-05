package com.resimanager.backoffice.controller;

import com.resimanager.backoffice.dto.MenuDto;
import com.resimanager.backoffice.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.resimanager.backoffice.utils.Constants.API_VERSION_PATH;

@RestController
@RequestMapping(value = API_VERSION_PATH)
@Validated

public class ViewsController {

    private final MenuService menuService;

    public ViewsController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuDto>> menuList(){
        return new ResponseEntity<>(menuService.menus(), HttpStatus.OK);
    }
}
