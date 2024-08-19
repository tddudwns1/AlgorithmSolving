select
    count(*) as FISH_COUNT,
    month(TIME) as MONTH
from
    FISH_INFO 
group by
    month(TIME)
order by
    MONTH