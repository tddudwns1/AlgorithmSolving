SELECT
    b.book_id as book_id,
    a.author_name as author_name,
    date_format(b.published_date, "%Y-%m-%d") as published_date
from
    author a
    join (
        select b.book_id as book_id , b.published_date as published_date, b.author_id
        from book b
        where b.category = '경제'
    ) b on a.author_id = b.author_id
order by
    b.published_date