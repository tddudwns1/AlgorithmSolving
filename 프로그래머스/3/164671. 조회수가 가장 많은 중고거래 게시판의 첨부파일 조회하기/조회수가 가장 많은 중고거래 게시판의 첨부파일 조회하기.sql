select
    concat("/home/grep/src/", b.BOARD_ID, "/", FILE_ID, FILE_NAME, FILE_EXT)
    as FILE_PATH
from
    USED_GOODS_FILE f
    join
    (
        select
            BOARD_ID
        from
            USED_GOODS_BOARD
        order by
            VIEWS desc
        limit 1
    ) b
        on f.BOARD_ID = b.BOARD_ID
order by
    FILE_ID desc