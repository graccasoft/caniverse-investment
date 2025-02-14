@param java.util.List<io.caniverse.investment.model.entity.Withdrawal> withdrawals
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Withdrawals")
</head>
<body
        x-data="{ page: 'withdrawals', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor = investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Withdrawals</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
                        <a href="/investor/withdraw"
                           class="inline-flex items-center justify-center gap-2.5 rounded-md bg-primary px-10 py-2 text-center font-medium text-white hover:bg-opacity-90 lg:px-8 xl:px-10">
                    Make a Withdrawal
                        </a>
                    </div>

                    <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                        <div class="col-span-3 flex items-center">
                            <p class="font-medium">Package</p>
                        </div>
                        <div class="col-span-2 items-center sm:flex">
                            <p class="font-medium">Details</p>
                        </div>
                        <div class="col-span-1 flex items-center">
                            <p class="font-medium">Status</p>
                        </div>

                    </div>

                    @for(var withdrawal: withdrawals)
                    <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                        <div class="col-span-3 flex items-center">
                            <div class="flex flex-col gap-4 sm:flex-row sm:items-center">

                                <p class="text-sm font-medium text-black dark:text-white">
                                    ${withdrawal.getInvestorInvestment().getInvestment().getName()}<br>
                                    ${withdrawal.getAmount()}
                                </p>
                            </div>
                        </div>

                        <div class="col-span-2 flex items-center">
                            <p class="text-sm font-medium text-black dark:text-white">
                                ${withdrawal.getAddress()}<br>
                                ${withdrawal.getCreatedOn().toString()}
                            </p>
                        </div>
                        <div class="col-span-1 flex items-center">
                            <p class="inline-flex rounded-full bg-success bg-opacity-10 px-3 py-1 text-sm font-medium text-success">
                                ${withdrawal.getStatus()}
                            </p>
                            @if(withdrawal.getStatus().equals(io.caniverse.investment.model.enums.TransactionStatus.APPROVED))
                            <small>${withdrawal.getTransactionHash()}</small>
                            @endif
                        </div>
                    </div>
                    @endfor

                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>