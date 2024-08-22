select
    concat('/home/grep/src/', f.BOARD_ID, '/', f.FILE_ID, f.FILE_NAME, f.FILE_EXT) as FILE_PATH
from
    USED_GOODS_FILE f
    join (
        select
            BOARD_ID
        from
            USED_GOODS_BOARD
        order by
            VIEWS desc
        limit
            1
    ) b
        on b.BOARD_ID = f.BOARD_ID
order by
    FILE_PATH desc