select
    c.ID,
    c.GENOTYPE,
    p.GENOTYPE & c.GENOTYPE as PARENT_GENOTYPE
from
    ECOLI_DATA c
    join
    ECOLI_DATA p
        on c.PARENT_ID = p.ID
where
    p.GENOTYPE & c.GENOTYPE = p.GENOTYPE
order by
    c.ID