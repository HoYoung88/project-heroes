import type { Ranking } from '~/types/Ranking';

export const useRanking = () => {
  const rankingList = ref<Ranking[]>([]);
  const pageRef = ref<Page>({ number: 1, totalElements: 0, size: 20, totalPages: 0 });

  const findAllHallOfHonorList = (pageNumber = 1, rankingDamageTypeFilter = 'physical') => {
    watchEffect(async () => {
      const { content, page } = await $fetch<{ content: Ranking[]; page: Page }>(
        `/api/ranking/hall-of-honor?rankingType=${rankingDamageTypeFilter}&page=${pageNumber}&perPage=${20}`,
      );

      if (content) {
        rankingList.value = content;
        pageRef.value = page;
      }
    });
  };

  const findAllRealTimeList = (pageNumber = 1, rankingDamageTypeFilter = 'physical') => {
    watchEffect(async () => {
      const { content, page } = await $fetch<{ content: Ranking[]; page: Page }>(
        `/api/ranking/real-time?rankingType=${rankingDamageTypeFilter}&page=${pageNumber}&perPage=${100}`,
      );

      if (content) {
        rankingList.value = content;
        pageRef.value = page;
      }
    });
  };

  return {
    rankingList,
    page: pageRef,
    findAllHallOfHonorList,
    findAllRealTimeList,
  };
};
