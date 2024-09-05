package com.resimanager.backoffice.persistance.repository;

import com.resimanager.backoffice.persistance.entity.MenuItem;
import com.resimanager.backoffice.persistance.entity.MenuItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, MenuItemId> {
    @Query("Select u from MenuItem u " +
            "where u.mItTipo='A' " +
            "order by u.id.mItID "
    )
    List<MenuItem> findMenus();

    @Query("Select u from MenuItem u " +
            "where u.mItItemPadre=?1 " +
            "order by u.id.mItID "
    )
    List<MenuItem> findSubMenus(Integer menuItemPadre);


}
