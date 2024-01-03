export class PageRequest {

    static readonly DEFAULT_PAGE_SIZE: number = 5;

    page: number = 0;
    size: number = PageRequest.DEFAULT_PAGE_SIZE;
    sort: string[] = [];

}
