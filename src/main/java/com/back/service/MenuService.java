package com.back.service;

import com.back.domain.Menu;
import com.back.domain.dto.MainMenuIF;
import com.back.domain.dto.MenuDto;
import com.back.domain.dto.MenuTreeIF;
import com.back.domain.params.MenuParam;
import com.back.repository.MenuCustomRepository;
import com.back.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final MenuCustomRepository menuCustomRepository;

    public List<MenuTreeIF> getMenuTree(String authRole) {
        return menuRepository.getMenuTree(authRole);
    }

    public Menu createMenu(MenuParam menuParam) {
        Menu menu = new Menu();
        menu.setCreateParam(menuParam);
        return  menuRepository.save(menu);
    }

    public MenuDto getMenu(Long id) {
        return menuCustomRepository.findMenuBy(id);
    }

    public void updateMenu(Long id,MenuParam menuParam) {
        Menu menu = menuRepository.findById(id).orElseThrow(RuntimeException::new);
        menu.setUpdateParam(menuParam);
        menuRepository.save(menu);
    }

    public int checkChildMenu(Long id) {
        return menuRepository.countByPrnMenuId(id);
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public List<MenuDto> getItemPrnMenus(String authRole) {
        return menuCustomRepository.findMenuByAuthRoleAndMenuLv(authRole, 1);
    }

    public List<MenuDto> getItemChildMenus(String authRole) {
        return menuCustomRepository.findMenuByAuthRoleAndMenuLv(authRole, 2);
    }


    public List<MenuDto> getMenusByAuth(MenuParam menuParam) {
        return menuCustomRepository.getMenusByAuth(menuParam);
    }

    public MainMenuIF getMainMenu(Long authId) {
        return menuRepository.getMainMenu(authId);
    }



}
