select
    count(*) as FISH_COUNT
from
    FISH_INFO i
    join
    FISH_NAME_INFO n
        on i.FISH_TYPE = n.FISH_TYPE
        and n.FISH_NAME in ('BASS', 'SNAPPER')