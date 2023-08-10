package com.back.repository;

import com.back.domain.Menu;
import com.back.domain.dto.MenuTreeIF;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository <Menu, Long> {
    String getMenuTreeSql =
        "with recursive recur_data as(\n"
            + "            select  id,\n"
            + "                    menu_nm,\n"
            + "                    menu_lv,\n"
            + "                    prn_menu_id,\n"
            + "                    url,\n"
            + "                    ord,\n"
            + "                    menu_type,\n"
            + "                    use_yn,\n"
            + "                    '' || id as menu_ids,\n"
            + "                    ord as prn_ord\n"
            + "            from    ws.tb_menu\n"
            + "            where   menu_lv = 1 and auth_role = :authRole\n"
            + "            union\n"
            + "            select  b.id,\n"
            + "                    b.menu_nm,\n"
            + "                    b.menu_lv,\n"
            + "                    b.prn_menu_id,\n"
            + "                    b.url,\n"
            + "                    b.ord,\n"
            + "                    b.menu_type,\n"
            + "                    b.use_yn,\n"
            + "                    a.menu_ids || ',' || b.id as menu_ids,\n"
            + "                    a.ord as prn_ord\n"
            + "            from    recur_data a\n"
            + "            inner join ws.tb_menu b on a.id = b.prn_menu_id and b.auth_role = :authRole\n"
            + "        )\n"
            + "        select  id as menuId,\n"
            + "                menu_nm as menuNm,\n"
            + "                menu_lv as menuLv,\n"
            + "                prn_menu_id as prnMenuId,\n"
            + "                url,\n"
            + "                ord,\n"
            + "                menu_type as menuType,\n"
            + "                use_yn as useYn,\n"
            + "                (select menu_nm from ws.tb_menu a where a.id = b.prn_menu_id) as prnMenuNm\n"
            + "        from    recur_data b\n"
            + "        order by prn_ord, menu_ids, ord";

    @Query(value = getMenuTreeSql, nativeQuery = true)
    List<MenuTreeIF> getMenuTree(@Param("authRole") String authRole);

    int countByPrnMenuId(Long id);

    List<Menu> findByMenuLvAndPrnMenuId(int menuLv, Long prnMenuId);

}

