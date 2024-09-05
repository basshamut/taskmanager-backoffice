package com.resimanager.backoffice.service;

import com.resimanager.backoffice.dto.MenuDto;
import com.resimanager.backoffice.persistance.entity.MenuItem;
import com.resimanager.backoffice.persistance.entity.MenuItemId;
import com.resimanager.backoffice.persistance.repository.MenuItemRepository;
import com.resimanager.backoffice.service.mapper.MnuToMnuDtoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    private final MenuItemRepository menuItemRepository;

    public MenuService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuDto> menus(){
        List<MenuItem> menuItems = menuItemRepository.findMenus();
        List<MenuDto> menusDto = new ArrayList<>();

        for (MenuItem menuItem : menuItems) {
            List<MenuDto> menusHijosDto = new ArrayList<>();
            MenuDto menuDto = new MnuToMnuDtoMapper().apply(menuItem);
            List<MenuItem> menusHijos= subMenus(menuItem.getId());
            for (MenuItem menuHijo : menusHijos) {
                MenuDto menuHijoDto =new MnuToMnuDtoMapper().apply(menuHijo);
                menuHijoDto.setSubmenus(new ArrayList<>());
                menusHijosDto.add(menuHijoDto);
            }
            menuDto.setSubmenus(menusHijosDto);
            menusDto.add(menuDto);
        }
        return menusDto;
    }

    public List<MenuItem> subMenus(MenuItemId menuid){
        return menuItemRepository.findSubMenus(menuid.getMItID());
    }
}
