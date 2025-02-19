@param java.util.List<io.caniverse.investment.model.entity.Investor> investors
@param io.caniverse.investment.model.entity.Investor investorQuery
@param org.springframework.security.web.csrf.CsrfToken csrfToken
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Investors")
</head>
<body
        x-data="{ page: 'investments', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.admin.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.admin-profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Investors</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
                        <div x-data="{ isOptionSelected: false }"
                             class="relative z-20 bg-transparent dark:bg-form-input">
                            <div class="relative w-full">
                                <form method="post" action="/admin/investors">
                                    <button type="submit" class="bg-white border absolute right-0 top-1/2 inline-flex -translate-y-1/2 cursor-pointer items-center gap-1 border-l border-gray-200 py-3 pl-3.5 pr-3 text-sm font-medium text-gray-700 dark:border-gray-800 dark:text-gray-400">
                                        <svg class="fill-current" width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" clip-rule="evenodd" d="M6.58822 4.58398C6.58822 4.30784 6.81207 4.08398 7.08822 4.08398H15.4154C15.6915 4.08398 15.9154 4.30784 15.9154 4.58398L15.9154 12.9128C15.9154 13.189 15.6916 13.4128 15.4154 13.4128H7.08821C6.81207 13.4128 6.58822 13.189 6.58822 12.9128V4.58398ZM7.08822 2.58398C5.98365 2.58398 5.08822 3.47942 5.08822 4.58398V5.09416H4.58496C3.48039 5.09416 2.58496 5.98959 2.58496 7.09416V15.4161C2.58496 16.5207 3.48039 17.4161 4.58496 17.4161H12.9069C14.0115 17.4161 14.9069 16.5207 14.9069 15.4161L14.9069 14.9128H15.4154C16.52 14.9128 17.4154 14.0174 17.4154 12.9128L17.4154 4.58398C17.4154 3.47941 16.52 2.58398 15.4154 2.58398H7.08822ZM13.4069 14.9128H7.08821C5.98364 14.9128 5.08822 14.0174 5.08822 12.9128V6.59416H4.58496C4.30882 6.59416 4.08496 6.81801 4.08496 7.09416V15.4161C4.08496 15.6922 4.30882 15.9161 4.58496 15.9161H12.9069C13.183 15.9161 13.4069 15.6922 13.4069 15.4161L13.4069 14.9128Z" fill=""></path>
                                        </svg>
                                        <div id="copy-text">Search</div>
                                    </button>

                                    <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
                                    <input type="text" placeholder="Search" name="name" value="${investorQuery.getName()}"
                                           class="border border-gray-300 h-11 px-5 rounded-lg shadow-theme-xs text-gray-800 text-sm w-full">
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-white/[0.03]">
                        <div class="max-w-full overflow-x-auto">
                            <div class="min-w-[1102px]">
                                <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                    <div class="col-span-3 flex items-center">
                                        <p class="font-medium">Name</p>

                                    </div>
                                    <div class="col-span-3 flex items-center">
                                        <p class="font-medium">Email</p>
                                    </div>

                                </div>

                                @for(var investor: investors)
                                    <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                        <div class="col-span-3 flex items-center">
                                            <p class="text-sm font-medium text-black dark:text-white">
                                                ${investor.getName()}<br>
                                                [ <a href="/admin/investors/${investor.getId()}/invest">Invest</a> ]
                                                [ <a href="/admin/investors/${investor.getId()}/withdraw">Withdraw</a> ]
                                            </p>
                                        </div>
                                        <div class="col-span-3 flex items-center">
                                            <div class="flex flex-col gap-4 sm:flex-row sm:items-center">
                                                ${investor.getUser().getUsername()}
                                            </div>
                                        </div>

                                    </div>
                                @endfor

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>