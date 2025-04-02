import dayjs from 'dayjs';

export const format = (date: string | null | undefined, format?: string) => {
  if (!date) {
    return '';
  }

  if (format) {
    return dayjs(date).add(9, 'h').format(format);
  } else {
    return dayjs(date).add(9, 'h').format('YYYY-MM-DD HH:mm:ss');
  }
};
