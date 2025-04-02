// import type { CharacterInfo } from '~/types/Character';

import type { CharacterInfo } from '~/types/Character';

export const useCharacterInfo = async (characterName: Ref<string>) => {
  const isError = ref(false);
  const characterInfo = ref<CharacterInfo | null>(null);

  const { data, error } = await useFetch<CharacterInfo>(`/api/character/${characterName.value}`);

  isError.value = !!error.value;

  if (data.value) {
    characterInfo.value = data.value;
  }

  return {
    characterInfo,
    isError,
  };
};
