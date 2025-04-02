<script setup lang="ts">
  const rankingTypes = [
    {
      id: 1,
      name: '공격력 기준 순위',
      value: 'physical',
    },
    {
      id: 2,
      name: '마법 공격력 기준 순위',
      value: 'magic',
    },
  ];
  const selectedRankingType = ref(rankingTypes[0]);
  const emit = defineEmits<{ (e: 'change', value: string) }>();

  watch(selectedRankingType, (newValue) => {
    emit('change', newValue.value);
  });
</script>

<template>
  <HeadlessListbox
    v-model="selectedRankingType"
    as="div"
    class="flex justify-end"
  >
    <div class="relative mt-2 w-60">
      <HeadlessListboxButton
        class="grid w-full cursor-pointer grid-cols-1 rounded-sm bg-white py-1.5 pr-2 pl-3 text-left text-gray-900 outline-1 -outline-offset-1 outline-gray-300 focus:outline-2 focus:-outline-offset-2 focus:outline-blue-600 sm:text-sm/6"
      >
        <span class="col-start-1 row-start-1 flex items-center gap-3 pr-6">
          <span class="block truncate">{{ selectedRankingType.name }}</span>
        </span>
      </HeadlessListboxButton>

      <transition
        leave-active-class="transition ease-in duration-100"
        leave-from-class="opacity-100"
        leave-to-class="opacity-0"
      >
        <HeadlessListboxOptions
          class="absolute z-10 mt-1 max-h-56 w-full overflow-auto rounded-md bg-white py-1 text-base ring-1 shadow-lg ring-black/5 focus:outline-hidden sm:text-sm"
        >
          <HeadlessListboxOption
            v-for="rankingType in rankingTypes"
            :key="rankingType.id"
            v-slot="{ active, selected }"
            as="template"
            :value="rankingType"
          >
            <li
              :class="[
                active ? 'bg-blue-600 text-white outline-hidden' : 'text-gray-900',
                'relative cursor-default py-2 pr-9 pl-3 select-none',
              ]"
            >
              <div class="flex items-center">
                <span
                  :class="[selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate']"
                  >{{ rankingType.name }}</span
                >
              </div>

              <span
                v-if="selected"
                :class="[
                  active ? 'text-white' : 'text-blue-600',
                  'absolute inset-y-0 right-0 flex items-center pr-4',
                ]"
              />
            </li>
          </HeadlessListboxOption>
        </HeadlessListboxOptions>
      </transition>
    </div>
  </HeadlessListbox>
</template>

<style scoped></style>
