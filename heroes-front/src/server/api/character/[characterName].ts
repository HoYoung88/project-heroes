import type { CharacterInfo } from '~/types/Character';

export default defineEventHandler(async (event) => {
  const config = useRuntimeConfig();
  const characterName = getRouterParam(event, 'characterName');

  if (characterName === undefined) {
    throw createError({
      statusCode: 400,
    });
  }

  const { data } = await $fetch<Api.Character.GetResponse<CharacterInfo>>(
    `/v1/character/${characterName}`,
    {
      baseURL: config.public.apiBaseURL,
      onResponseError: ({ response }) => {
        if (response.status === 400) {
          throw createError({
            statusCode: response.status,
            statusMessage: response.statusText,
            message: '캐릭터가 존재하지 않습니다. 검색어를 다시 확인해주세요.',
            data: { field: 'characterName' },
          });
        }
      },
    },
  );

  return data;
});
