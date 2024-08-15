SELECT
    USER_ID,
    NICKNAME,
    concat(CITY, ' ', STREET_ADDRESS1, ' ', STREET_ADDRESS2) as 전체주소,
    concat(left(TLNO, 3), '-', substr(TLNO, 4, 4), '-', right(TLNO, 4)) as 전화번호
from
    USED_GOODS_USER u
    right join USED_GOODS_BOARD b on u.USER_ID = b.WRITER_ID
group by
    b.WRITER_ID
having
    count(*) >= 3
order by
    u.USER_ID desc