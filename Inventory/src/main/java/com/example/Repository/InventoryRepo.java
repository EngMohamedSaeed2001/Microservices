package com.example.Repository;

import com.example.DB.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {



    List<Inventory> findBySkcodeIn(List<String> code);
}
