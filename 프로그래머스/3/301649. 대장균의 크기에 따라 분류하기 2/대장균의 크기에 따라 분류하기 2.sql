select
    ID,
    case
        when percent_rank() over (order by SIZE_OF_COLONY) <= 0.25 then "LOW"
        when percent_rank() over (order by SIZE_OF_COLONY) <= 0.50 then "MEDIUM"
        when percent_rank() over (order by SIZE_OF_COLONY) <= 0.75 then "HIGH"
        else "CRITICAL"
    end as COLONY_NAME
from
    ECOLI_DATA
order by
    ID















# select
#     ID,
#     case
#         when tile = 1 then 'LOW'
#         when tile = 2 then 'MEDIUM' 
#         when tile = 3 then 'HIGH' 
#         else 'CRITICAL' 
#     end
#     as COLONY_NAME
# from
#     (
#         select
#             ID,
#             count(*) OVER () as total,
#             ntile(4) over (order by SIZE_OF_COLONY) as tile
#         from
#             ECOLI_DATA 
#     ) r
# order by
#     ID