select
    s.ROUTE as ROUTE,
    concat(round(sum(s.D_BETWEEN_DIST), 1), "km") as TOTAL_DISTANCE,
    concat(round(avg(s.D_BETWEEN_DIST), 2), "km") as AVERAGE_DISTANCE
from
    SUBWAY_DISTANCE s
group by
    ROUTE
order by
    sum(s.D_BETWEEN_DIST) desc