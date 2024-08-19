select
    ITEM_ID,
    ITEM_NAME,
    RARITY
from
    ITEM_INFO i
where
    not EXISTS  (
        select
            1
        from
            ITEM_TREE t
        WHERE
            t.PARENT_ITEM_ID = i.ITEM_ID
    )
order by
    ITEM_ID desc