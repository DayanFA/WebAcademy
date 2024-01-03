export type PageResponse<T> = {
    content: T[],
    totalPages: number,
    totalElements: number,
    size: number,
    number: number,
    numberOfElements: number
}
