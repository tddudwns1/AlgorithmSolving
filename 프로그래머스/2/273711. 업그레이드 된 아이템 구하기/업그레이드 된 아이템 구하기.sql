select
    i.ITEM_ID,
    i.ITEM_NAME,
    i.RARITY
from
    ITEM_INFO i
    join
    (
        select
            t.ITEM_ID as ITEM_ID
        from
            ITEM_INFO i
            join
            ITEM_TREE t
                on i.ITEM_ID = t.PARENT_ITEM_ID
                and i.RARITY = 'RARE'
    ) s on i.ITEM_ID = s.ITEM_ID
order by
    i.ITEM_ID desc