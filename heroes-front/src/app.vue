<script setup lang="ts">
  provideHeadlessUseId(() => useId());
</script>

<template>
  <NuxtRouteAnnouncer />
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
