<script setup lang="ts">
  const menus = ref([
    // {
    //   text: '홈',
    //   href: '/',
    // },
    {
      text: '랭킹',
      href: '/ranking',
    },
  ]);
</script>

<template>
  <ul
    class="z-50 max-lg:fixed max-lg:top-0 max-lg:left-0 max-lg:h-full max-lg:w-1/2 max-lg:min-w-[300px] max-lg:space-y-3 max-lg:overflow-auto max-lg:bg-white max-lg:p-6 max-lg:shadow-md lg:ml-14 lg:flex lg:gap-x-5"
  >
    <HeaderMenuItem
      v-for="(menu, index) in menus"
      :key="index"
      :text="menu.text"
      :href="menu.href"
    />
  </ul>
</template>

<style scoped></style>
