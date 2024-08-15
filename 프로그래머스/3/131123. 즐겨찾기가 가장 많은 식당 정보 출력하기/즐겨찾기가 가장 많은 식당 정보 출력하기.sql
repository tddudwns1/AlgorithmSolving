SELECT
    FOOD_TYPE,
    REST_ID,
    REST_NAME,
    FAVORITES
from
    (
        select
            FOOD_TYPE,
            REST_ID,
            REST_NAME,
            FAVORITES,
            rank() over (partition by FOOD_TYPE order by FAVORITES desc) as ranking
        from
            REST_INFO
    ) r
where
    r.ranking = 1
order by
    FOOD_TYPE desc
