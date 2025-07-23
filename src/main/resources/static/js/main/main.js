async function restTest() {
  try {
    const data = await restApi.read('/sample/test2', { id: 1 });
    console.log(data);
  } catch (error) {
    console.error('API 요청 실패:', error);
  }
}