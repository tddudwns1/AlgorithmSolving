select
    child.ID,
    child.GENOTYPE,
    parent.GENOTYPE as PARENT_GENOTYPE
from
    ECOLI_DATA child
    join
    ECOLI_DATA parent
        on child.PARENT_ID = parent.ID
        and parent.GENOTYPE & child.GENOTYPE = parent.GENOTYPE
order by
    ID

















# select
#     c.ID,
#     c.GENOTYPE,
#     p.GENOTYPE as PARENT_GENOTYPE
# from
#     ECOLI_DATA c
#     join
#     ECOLI_DATA p
#         on c.PARENT_ID = p.ID
# where
#     p.GENOTYPE & c.GENOTYPE = p.GENOTYPE
# order by
#     c.ID