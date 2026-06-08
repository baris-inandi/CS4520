# A7 Book Finder — Student Skeleton

Starter project matching the **Assignment 7** handout. The data-layer plumbing, navigation,
one ViewModel (`SearchViewModel`), and `BookCard` are provided complete.

**You write** (search for `✍️ TODO`):
- `model/dto/OpenLibraryDto.kt` — the DTOs
- `ui/common/BookSearchBar.kt` — keyboard "Search" action + clear button
- `ui/search/SearchScreen.kt` — validation (Snackbar), empty-results message, result rows
- `ui/detail/BookDetailViewModel.kt` — the load + IOException/HttpException handling
- `ui/detail/BookDetailScreen.kt` — render the detail fields (+ optional ⭐ bonus cover image)

> ⚠️ This skeleton **does not compile until you fill the TODOs** — the DTOs start empty, and the
> provided `Mappers.kt` / `BookRepository.kt` reference DTO fields you still need to add.

**Before submitting:** rename the project folder to `A7_<Your_Last_Name>_<Last_4_digits_of_your_NUID>`
and zip it. See the Assignment 7 description on the course site for the full instructions and rubric.
