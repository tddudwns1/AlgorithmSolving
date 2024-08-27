select
    ID,
    case
        when r.ranking <= total / 4 then 'LOW'
        when r.ranking <= total / 2 then 'MEDIUM' 
        when r.ranking <= total * 3 / 4 then 'HIGH' 
        else 'CRITICAL' 
    end
    as COLONY_NAME
from
    (
        select
            ID,
            count(*) OVER () as total,
            rank() over (order by SIZE_OF_COLONY) as ranking
        from
            ECOLI_DATA 
    ) r
order by
    ID