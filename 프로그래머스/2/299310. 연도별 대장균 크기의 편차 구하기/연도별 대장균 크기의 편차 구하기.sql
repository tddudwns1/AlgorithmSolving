select
    year(DIFFERENTIATION_DATE) as YEAR,       # 분화된 연도
    (
        select
            max(SIZE_OF_COLONY)
        from
            ECOLI_DATA
        where
            YEAR = year(DIFFERENTIATION_DATE)
        group by
            year(DIFFERENTIATION_DATE)
    ) - SIZE_OF_COLONY as YEAR_DEV,   # 분화된 연도별 대장균 크기의 편차: 분화된 연도별 가장 큰 대장균의 크기 - 각 대장균의 크기
    ID              # 대장균 개체의 ID
from
    ECOLI_DATA      # 실험실에서 배양한 대장균들의 정보
order by            # 연도에 대해 오름차순, 대장균 크기의 편차에 대해 오름차순
    YEAR,
    YEAR_DEV