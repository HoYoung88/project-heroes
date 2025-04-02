<script setup lang="ts">
  const { rankingList, page, findAllHallOfHonorList } = useRanking();

  findAllHallOfHonorList(1);

  const totalPages = computed(() => page.value.totalPages ?? 0);
  const totalElements = computed(() => page.value.totalElements);
  const computedPageNumber = computed(() => page.value.number);
  const rankingDamageTypeFilterRef = ref('physical');

  const onPageMove = (pageNumber: number) => {
    findAllHallOfHonorList(pageNumber, rankingDamageTypeFilterRef.value);
  };

  const rankingNum = (index: number) => {
    return index + totalPages.value * (computedPageNumber.value - 1) + 1;
  };

  const changeRankingDamageTypeFilter = (value: string) => {
    rankingDamageTypeFilterRef.value = value;
    findAllHallOfHonorList(1, value);
  };

  const goCharacterInfoLink = (characterName: string) => {
    return `/character-info/${characterName}`;
  };
</script>

<template>
  <HeadlessTabPanel class="flex flex-col">
    <RankingDamageTypeFilter @change="changeRankingDamageTypeFilter" />
    <div class="overflow-x-auto pt-2">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-100 whitespace-nowrap">
          <tr>
            <th
              class="px-4 py-4 text-left text-xs font-semibold tracking-wider text-slate-900 uppercase"
            >
              순위
            </th>
            <th
              class="px-4 py-4 text-left text-xs font-semibold tracking-wider text-slate-900 uppercase"
            >
              캐릭터 이름
            </th>
            <th
              class="px-4 py-4 text-left text-xs font-semibold tracking-wider text-slate-900 uppercase"
            >
              스코어
            </th>
          </tr>
        </thead>

        <tbody class="divide-y divide-gray-200 bg-white whitespace-nowrap">
          <tr
            v-for="(item, index) in rankingList"
            :key="index"
          >
            <td class="px-4 py-4 text-sm font-medium text-slate-900">
              {{ rankingNum(index) }}
            </td>
            <td class="px-4 py-4 text-sm font-medium text-slate-600">
              <NuxtLink :to="goCharacterInfoLink(item.characterName)">
                {{ item.characterName }}
              </NuxtLink>
            </td>
            <td class="px-4 py-4 text-sm font-medium text-slate-600">{{ item.score }}</td>
          </tr>
        </tbody>
      </table>

      <TuiPagination
        :items-per-page="totalPages"
        :total-items="totalElements"
        @page-move="onPageMove"
      />
    </div>
  </HeadlessTabPanel>
</template>

<style scoped></style>
