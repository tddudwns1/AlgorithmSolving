select
    count(*) as COUNT
    # *
from
    ECOLI_DATA
where
    not (GENOTYPE & (1 << 1)) != 0
    and (GENOTYPE & ((1 << 2) + 1)) != 0