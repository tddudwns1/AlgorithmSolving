SELECT
    FLAVOR
from
    FIRST_HALF
    join ICECREAM_INFO using(FLAVOR)
where
    INGREDIENT_TYPE = "fruit_based"
    and TOTAL_ORDER > 3000
order by
    TOTAL_ORDER desc