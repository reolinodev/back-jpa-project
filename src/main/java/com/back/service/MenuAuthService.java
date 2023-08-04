package com.back.service;

import com.back.domain.Menu;
import com.back.domain.MenuAuth;
import com.back.domain.dto.MenuAuthDto;
import com.back.domain.params.MenuAuthParam;
import com.back.domain.params.MenuAuthRow;
import com.back.repository.AuthRepository;
import com.back.repository.MenuAuthCustomRepository;
import com.back.repository.MenuAuthRepository;
import com.back.repository.MenuRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuAuthService {

    private final MenuAuthRepository menuAuthRepository;
    private final MenuAuthCustomRepository menuAuthCustomRepository;

    private final MenuRepository menuRepository;
    private final AuthRepository authRepository;


    public List<MenuAuthDto> getMenuAuths(Long menuId) {
        return menuAuthCustomRepository.findAllMenuAuth(menuId);
    }

    public void saveMenuAuths(MenuAuthParam menuAuthParam) {
        MenuAuthRow[] updatedRows = menuAuthParam.updatedRows;

        Menu menu = menuRepository.findById(menuAuthParam.menuId).orElseThrow(RuntimeException::new);

        //상위 레벨인 경우 하위 메뉴에 권한을 부여한다. 하위 레벨은 해당 메뉴만 권한을 부여한다.
        if(menu.menuLv == 1){
            for (MenuAuthRow updatedRow : updatedRows ) {

                MenuAuth menuAuth = menuAuthCustomRepository.findByMenuIdAndAuthId(menu.id, updatedRow.authId);

                if(menuAuth== null){
                    menuAuth = new MenuAuth();
                    menuAuth.auth = authRepository.findById(updatedRow.authId).orElseThrow(RuntimeException::new);
                    menuAuth.menu = menu;
                    menuAuth.useYn = updatedRow.useYn;
                    menuAuth.createdId = menuAuthParam.updatedId;
                }else{
                    menuAuth.useYn = updatedRow.useYn;
                    menuAuth.updatedId = menuAuthParam.updatedId;
                }

                menuAuthRepository.save(menuAuth);
            }

            List<Menu> childMenus = menuRepository.findByMenuLvAndPrnMenuId(2, menu.id);

            for (Menu childMenu : childMenus ) {
                for (MenuAuthRow updatedRow : updatedRows ) {

                    MenuAuth menuAuth = menuAuthCustomRepository.findByMenuIdAndAuthId(childMenu.id, updatedRow.authId);

                    if(menuAuth == null){
                        menuAuth = new MenuAuth();
                        menuAuth.auth = authRepository.findById(updatedRow.authId).orElseThrow(RuntimeException::new);
                        menuAuth.menu = childMenu;
                        menuAuth.useYn = updatedRow.useYn;
                        menuAuth.createdId = menuAuthParam.updatedId;
                    }else{
                        menuAuth.useYn = updatedRow.useYn;
                        menuAuth.updatedId = menuAuthParam.updatedId;
                    }

                    menuAuthRepository.save(menuAuth);
                }
            }
        }else {
            for (MenuAuthRow updatedRow : updatedRows ) {

                MenuAuth menuAuth = menuAuthCustomRepository.findByMenuIdAndAuthId(menu.id, updatedRow.authId);

                if(menuAuth.id == null){
                    menuAuth = new MenuAuth();
                    menuAuth.auth = authRepository.findById(updatedRow.authId).orElseThrow(RuntimeException::new);
                    menuAuth.menu = menu;
                    menuAuth.useYn = updatedRow.useYn;
                    menuAuth.createdId = menuAuthParam.updatedId;
                }else{
                    menuAuth.useYn = updatedRow.useYn;
                    menuAuth.updatedId = menuAuthParam.updatedId;
                }

                menuAuthRepository.save(menuAuth);
            }
        }
    }
}
