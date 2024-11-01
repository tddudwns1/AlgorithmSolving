select
    fi.ID,
    fni.FISH_NAME,
    fi.LENGTH
from
    FISH_NAME_INFO fni
    join
    FISH_INFO fi
        on fni.FISH_TYPE = fi.FISH_TYPE
    join (
        select
            FISH_TYPE,
            max(LENGTH) as LENGTH
        from
            FISH_INFO 
        group by
            FISH_TYPE
    ) m
        on m.FISH_TYPE = fi.FISH_TYPE
        and m.LENGTH = fi.LENGTH
order by
    ID















# select
#     o.ID as ID,
#     ni.FISH_NAME as FISH_NAME,
#     o.LENGTH as LENGTH
# from
#     FISH_INFO o
#     join
#     FISH_NAME_INFO ni
#         on o.FISH_TYPE = ni.FISH_TYPE
#     join
#     (
#         select
#             ni.FISH_TYPE as FISH_TYPE,
#             max(i.LENGTH) as LENGTH
#         from
#             FISH_INFO i
#             join
#             FISH_NAME_INFO ni
#                 on i.FISH_TYPE = ni.FISH_TYPE
#         group by
#             FISH_TYPE
#     ) c
#     on o.FISH_TYPE = c.FISH_TYPE and o.LENGTH = c.LENGTH
# order by
#     ID