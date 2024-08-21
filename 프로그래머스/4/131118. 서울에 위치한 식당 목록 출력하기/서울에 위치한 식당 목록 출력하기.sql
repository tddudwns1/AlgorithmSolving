SELECT
    i.REST_ID,
    i.REST_NAME,
    i.FOOD_TYPE,
    i.FAVORITES,
    i.ADDRESS,
    round(avg(r.REVIEW_SCORE),2) as SCORE
from
    REST_INFO i
    join
    REST_REVIEW r
        on i.REST_ID = r.REST_ID
where
    ADDRESS like '서울%'
group by
    REST_ID
order by
    SCORE desc,
    FAVORITES desc