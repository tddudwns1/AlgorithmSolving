select
    u.ITEM_ID,
    u.ITEM_NAME,
    u.RARITY
from
    ITEM_INFO i
    join
    ITEM_TREE t
        on i.RARITY = "RARE"
        and i.ITEM_ID = t.PARENT_ITEM_ID
    join
    ITEM_INFO u
        on t.ITEM_ID = u.ITEM_ID

order by
    ITEM_ID desc















# select
#     i2.ITEM_ID,
#     i2.ITEM_NAME,
#     i2.RARITY
# from
#     ITEM_INFO i
#     join
#     ITEM_TREE t
#         on i.ITEM_ID = t.PARENT_ITEM_ID
#         and i.RARITY = 'RARE'
#     join
#     ITEM_INFO i2
#         on t.ITEM_ID = i2.ITEM_ID
# order by
#     ITEM_ID desc