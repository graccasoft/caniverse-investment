@param java.util.List<io.caniverse.investment.model.entity.InvestorInvestment> investments
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Investments")
</head>
<body
        x-data="{ page: 'investments', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor = investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Investments</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
                        <a href="/investor/invest"
                           class="inline-flex items-center justify-center gap-2.5 rounded-md bg-primary px-10 py-2 text-center font-medium text-white hover:bg-opacity-90 lg:px-8 xl:px-10">
                            Make an Investment
                        </a>
                    </div>
                    <div class="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-white/[0.03]">
                        <div class="max-w-full overflow-x-auto">
                            <div class="min-w-[1102px]">
                                <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                    <div class="col-span-3 flex items-center">
                                        <p class="font-medium">Package</p>
                                    </div>
                                    <div class="col-span-2 items-center sm:flex">
                                        <p class="font-medium">Date</p>
                                    </div>
                                    <div class="col-span-1 flex items-center">
                                        <p class="font-medium">Status</p>
                                    </div>

                                </div>

                                @for(var investment: investments)
                                    <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                        <div class="col-span-3 flex items-center">
                                            <div class="flex flex-col gap-4 sm:flex-row sm:items-center">

                                                <p class="text-sm font-medium text-black dark:text-white">
                                                    ${investment.getInvestment().getName()}<br>
                                                    <smaal>$${investment.getAmount()}</smaal>
                                                </p>
                                            </div>
                                        </div>

                                        <div class="col-span-2 flex items-center">
                                            <p class="text-sm font-medium text-black dark:text-white">${ io.caniverse.investment.utils.WebUtils.formatDate(investment.getCreatedOn())}</p>
                                        </div>
                                        <div class="col-span-1 flex items-center">
                                            <p class="inline-flex rounded-full bg-success bg-opacity-10 px-3 py-1 text-sm font-medium text-success">
                                                ${investment.getStatus()}
                                            </p>
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