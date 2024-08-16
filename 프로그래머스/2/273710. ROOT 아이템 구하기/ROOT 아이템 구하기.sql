select
    i.ITEM_ID,
    ITEM_NAME
from
    ITEM_INFO i
    join
    ITEM_TREE t
        on i.ITEM_ID = t.ITEM_ID
where
    PARENT_ITEM_ID is null
order by
    ITEM_ID