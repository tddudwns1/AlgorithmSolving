select
    q.QUARTER,
    count(*) as ECOLI_COUNT
from
    (
        select
            case
                when month(DIFFERENTIATION_DATE) between 1 and 3 then "1Q"
                when month(DIFFERENTIATION_DATE) between 4 and 6 then "2Q"
                when month(DIFFERENTIATION_DATE) between 7 and 9 then "3Q"
                else "4Q"
            end
            as QUARTER
        from
            ECOLI_DATA
    ) q
group by
    QUARTER
order by
    QUARTER
















# select
#     s.QUARTER as QUARTER,
#     count(*) as ECOLI_COUNT
# from
#     (
#         select
#             case
#                 when month(DIFFERENTIATION_DATE) between 1 and 3 then '1Q'
#                 when month(DIFFERENTIATION_DATE) between 4 and 6 then '2Q'
#                 when month(DIFFERENTIATION_DATE) between 7 and 9 then '3Q'
#                 else '4Q'
#             end
#             as QUARTER
#         from
#             ECOLI_DATA 
#     ) s
# group by
#     s.QUARTER
# ORDER BY
#     s.QUARTER