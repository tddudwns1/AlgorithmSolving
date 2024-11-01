select
    i.ITEM_ID,
    ITEM_NAME,
    RARITY
from
    ITEM_INFO i
    left join
    ITEM_TREE t
        on t.PARENT_ITEM_ID = i.ITEM_ID
where
    t.PARENT_ITEM_ID is null
order by
    ITEM_ID desc
















# SELECT
#     i.ITEM_ID,
#     i.ITEM_NAME,
#     i.RARITY
# FROM
#     ITEM_INFO i
# LEFT JOIN
#     ITEM_TREE t ON i.ITEM_ID = t.PARENT_ITEM_ID
# WHERE
#     t.PARENT_ITEM_ID IS NULL
# ORDER BY
#     i.ITEM_ID DESC;