select
    year(DIFFERENTIATION_DATE) as YEAR,
    max(SIZE_OF_COLONY) over(partition by year(DIFFERENTIATION_DATE)) - SIZE_OF_COLONY as YEAR_DEV,
    ID
from
    ECOLI_DATA

order by
    YEAR,
    YEAR_DEV