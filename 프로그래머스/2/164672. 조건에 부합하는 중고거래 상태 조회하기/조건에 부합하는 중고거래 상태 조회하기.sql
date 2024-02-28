-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE, 
    CASE
        when STATUS = 'SALE' then '판매중'
        when STATUS = 'RESERVED' then '예약중'
        when STATUS = 'DONE' then '거래완료'
    end as status
from USED_GOODS_BOARD
where '2022-10-05' = CREATED_DATE
order by BOARD_ID desc