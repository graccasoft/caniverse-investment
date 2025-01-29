<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Investments")
</head>
<body
        x-data="{ page: 'withdrawals', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.admin.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Withdrawal</h2>

                <div class="mb-10 rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="border-b border-stroke px-4 py-4 dark:border-strokedark sm:px-6 xl:px-9">
                        <h3 class="text-2xl font-medium text-black dark:text-white">
                            Order #15478
                        </h3>
                    </div>

                    <div class="p-4 sm:p-6 xl:p-9">

                        <div class="-mx-4 flex flex-wrap">
                            <div class="w-full px-4 sm:w-1/2 xl:w-3/12">
                                <div class="mb-10">
                                    <h4 class="mb-4 text-title-sm2 font-medium leading-[30px] text-black dark:text-white md:text-2xl">
                                        Package
                                    </h4>
                                    <p class="font-medium">
                                        Long term<br>
                                        $1,000.00
                                    </p>
                                </div>
                            </div>
                            <div class="w-full px-4 sm:w-1/2 xl:w-3/12">
                                <div class="mb-10">
                                    <h4 class="mb-4 text-title-sm2 font-medium leading-[30px] text-black dark:text-white md:text-2xl">
                                        Payment Details
                                    </h4>
                                    <p class="font-medium">
                                        withdrawal_address<br>
                                        withdrawal_network
                                    </p>
                                </div>
                            </div>
                            <div class="w-full px-4 xl:w-6/12">
                                <div class="mr-10 text-right md:ml-auto">
                                    <div class="ml-auto sm:w-1/2">
                                        <p class="mb-4 flex justify-between font-medium text-black dark:text-white">
                                            <span> Investor name: <span> John doe </span></span>

                                        </p>
                                        <p class="mb-4 flex justify-between font-medium text-black dark:text-white">
                                            <span> Investor email: <span> joh.doe@fake.com </span></span>

                                        </p>
                                        <p>
                                        <div>
                                            <input type="text" placeholder="Transaction Tx Id" class="w-full rounded-lg border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                        </div>
                                        </p>

                                    </div>

                                    <div class="mt-10 flex flex-col justify-end gap-4 sm:flex-row">
                                        <button class="flex items-center justify-center rounded border border-primary px-8 py-2.5 text-center font-medium text-primary hover:opacity-90">
                                            Decline Request
                                        </button>
                                        <button class="flex items-center justify-center rounded bg-primary px-8 py-2.5 text-center font-medium text-gray hover:bg-opacity-90">
                                            Approve Request
                                        </button>
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