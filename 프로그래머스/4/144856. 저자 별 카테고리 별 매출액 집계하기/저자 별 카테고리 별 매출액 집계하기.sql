SELECT
    b.AUTHOR_ID,
    a.AUTHOR_NAME,
    b.CATEGORY,
    sum(s.SALES * b.PRICE) as TOTAL_SALES
from
    BOOK b
    join
    AUTHOR a
        on b.AUTHOR_ID = a.AUTHOR_ID
    join
    BOOK_SALES s
        on b.BOOK_ID = s.BOOK_ID
        and year(SALES_DATE) = 2022
        and month(SALES_DATE) = 1
group by
    b.CATEGORY, b.AUTHOR_ID
order by
    AUTHOR_ID,
    CATEGORY desc