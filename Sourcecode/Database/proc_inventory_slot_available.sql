DELIMITER //
CREATE PROCEDURE proc_inventory_slot_available(
    IN shop_item_id int(32), 
    IN the_avatar_id int(32),
    OUT available int(1))
BEGIN
    DECLARE type_item_count INT DEFAULT 0;
    DECLARE slot_id INT DEFAULT 0;
    DECLARE item_type_limit INT DEFAULT 0;
    
    SELECT slot_id INTO slot_id
    FROM SHOP_ITEMS
    WHERE id = shop_item_id;
    
    SELECT equip_limit INTO item_type_limit 
    FROM AVATAR_ITEM_SLOTS
    WHERE id = slot_id;
    
    SELECT COUNT(*) INTO type_item_count
    FROM SHOP_ITEMS 
    WHERE id IN (SELECT shop_item_id 
                  FROM AVATAR_INVENTORIES 
                  WHERE avatar_id = the_avatar_id)
    AND slot_id = slot_id;
    
    IF type_item_count < item_type_limit THEN
        SET available = 1;
    ELSE
        SET available = 0;
    END IF;
END//
DELIMITER ;