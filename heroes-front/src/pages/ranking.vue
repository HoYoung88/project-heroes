<script setup lang="ts">
  const tabList = ['명예의 전당', '실시간 랭킹'];
</script>

<template>
  <BaseCard class="mx-auto max-w-4xl px-4 sm:px-6 lg:max-w-screen-2xl lg:px-8">
    <HeadlessTabGroup
      as="div"
      class="grid grid-cols-1 gap-4 lg:col-span-2"
    >
      <HeadlessTabList
        as="div"
        class="flex justify-center space-x-2 p-2"
      >
        <HeadlessTab
          v-for="(tabName, index) in tabList"
          v-slot="{ selected }"
          :key="index"
          as="template"
        >
          <button
            :class="[
              'cursor-pointer rounded px-4 py-2 text-gray-400 hover:bg-blue-600 hover:text-white',
              selected ? 'bg-blue-600 text-white' : '',
            ]"
          >
            {{ tabName }}
          </button>
        </HeadlessTab>
      </HeadlessTabList>

      <HeadlessTabPanels
        class="mt-2"
        selected-index="0"
      >
        <RankingHallOfHonorTabPanel />
        <RankingRealTimeTabPanel />
      </HeadlessTabPanels>
    </HeadlessTabGroup>
  </BaseCard>
</template>

<style scoped></style>
