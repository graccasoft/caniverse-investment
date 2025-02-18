@param io.caniverse.investment.model.entity.Investor investor
@param String baseUrl
@param java.util.List<io.caniverse.investment.model.entity.Investor> referrals
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Profile")
</head>
<body
        x-data="{ page: 'invest', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor=investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <div class="rounded-2xl border border-gray-200 bg-white p-5 dark:border-gray-800 dark:bg-white/[0.03] lg:p-6">
                    <h3 class="mb-5 text-lg font-semibold text-gray-800 dark:text-white/90 lg:mb-7">
                        Profile
                    </h3>
                    @template.investor.ref-link(investor = investor, baseUrl = baseUrl)
                    <div class="p-5 mb-6 border border-gray-200 rounded-2xl dark:border-gray-800 lg:p-6">
                        <div class="flex flex-col gap-6 lg:flex-row lg:items-start lg:justify-between">
                            <div>
                                <h4 class="text-lg font-semibold text-gray-800 dark:text-white/90 lg:mb-6">
                                    Personal Information
                                </h4>

                                <div class="grid grid-cols-1 gap-4 lg:grid-cols-2 lg:gap-7 2xl:gap-x-32">
                                    <div>
                                        <p class="mb-2 text-xs leading-normal text-gray-500 dark:text-gray-400">
                                            Name
                                        </p>
                                        <p class="text-sm font-medium text-gray-800 dark:text-white/90">
                                            ${investor.getName()}
                                        </p>
                                    </div>

                                    <div>
                                        <p class="mb-2 text-xs leading-normal text-gray-500 dark:text-gray-400">
                                            Email address
                                        </p>
                                        <p class="text-sm font-medium text-gray-800 dark:text-white/90">
                                            ${investor.getUser().getUsername()}
                                        </p>
                                    </div>


                                </div>
                            </div>

                            <h4 class="text-lg font-semibold text-gray-800 dark:text-white/90 lg:mb-6">
                                My Team
                            </h4>
                            <div class="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-white/[0.03]">
                                <div class="max-w-full overflow-x-auto">
                                    <div class="min-w-[1102px]">
                                        <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                            <div class="col-span-1 flex items-center">
                                                <p class="font-medium">Id</p>
                                            </div>
                                            <div class="col-span-5 items-center sm:flex">
                                                <p class="font-medium">Name</p>
                                            </div>

                                        </div>

                                        @for(var referral: referrals)
                                            <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                                <div class="col-span-1 flex items-center">
                                                    <div class="flex flex-col gap-4 sm:flex-row sm:items-center">

                                                        <p class="text-sm font-medium text-black dark:text-white">
                                                           ${referral.getId()}
                                                        </p>
                                                    </div>
                                                </div>

                                                <div class="col-span-5 flex items-center">
                                                    <p class="text-sm font-medium text-black dark:text-white">
                                                       ${referral.getName()}
                                                    </p>
                                                </div>

                                            </div>
                                        @endfor
                                    </div>
                                </div>
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