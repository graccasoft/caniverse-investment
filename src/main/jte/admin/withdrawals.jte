@param java.util.List<io.caniverse.investment.model.entity.Withdrawal> withdrawals
@param io.caniverse.investment.model.enums.TransactionStatus status
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
    @template.admin.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.admin-profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Withdrawals</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
                        <div x-data="{ isOptionSelected: false }"
                             class="relative z-20 bg-transparent dark:bg-form-input">
                            <select class="relative z-20 w-full appearance-none rounded border border-stroke bg-transparent px-5 py-3 outline-none transition focus:border-primary active:border-primary dark:border-form-strokedark dark:bg-form-input dark:focus:border-primary"
                                    :class="isOptionSelected &amp;&amp; 'text-black dark:text-white'"
                                    @change="isOptionSelected = true" id="status">
                                <option value="" class="text-body">
                                    Select Status
                                </option>
                                <option value="PENDING"  class="text-body">Pending</option>
                                <option value="APPROVED" class="text-body">Approved</option>
                                <option value="DENIED" class="text-body">Denied</option>
                            </select>
                            <span class="absolute right-4 top-1/2 z-30 -translate-y-1/2">
                                        <svg class="fill-current" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                          <g opacity="0.8">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                  d="M5.29289 8.29289C5.68342 7.90237 6.31658 7.90237 6.70711 8.29289L12 13.5858L17.2929 8.29289C17.6834 7.90237 18.3166 7.90237 18.7071 8.29289C19.0976 8.68342 19.0976 9.31658 18.7071 9.70711L12.7071 15.7071C12.3166 16.0976 11.6834 16.0976 11.2929 15.7071L5.29289 9.70711C4.90237 9.31658 4.90237 8.68342 5.29289 8.29289Z"
                                                  fill=""></path>
                                          </g>
                                        </svg>
                                    </span>
                        </div>
                    </div>
                    <div class="overflow-hidden rounded-xl border border-gray-200 bg-white dark:border-gray-800 dark:bg-white/[0.03]">
                        <div class="max-w-full overflow-x-auto">
                            <div class="min-w-[1102px]">
                                <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                    <div class="col-span-2 flex items-center">
                                        <p class="font-medium">Investor</p>
                                    </div>
                                    <div class="col-span-2 flex items-center">
                                        <p class="font-medium">Package</p>
                                    </div>
                                    <div class="col-span-2 items-center sm:flex">
                                        <p class="font-medium">Amount</p>
                                    </div>
                                    <div class="col-span-1 flex items-center">
                                        <p class="font-medium">Status</p>
                                    </div>

                                </div>

                                @for(var withdrawal: withdrawals)
                                <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
                                    <div class="col-span-2 flex items-center">
                                        <p class="text-sm font-medium text-black dark:text-white">
                                            ${withdrawal.getInvestorInvestment().getInvestor().getName()}<br>
                                            [ <a href="/admin/withdrawals/${withdrawal.getId()}">View</a> ]
                                        </p>
                                    </div>
                                    <div class="col-span-2 flex items-center">
                                        <div class="flex flex-col gap-4 sm:flex-row sm:items-center">
                                            ${withdrawal.getInvestorInvestment().getInvestment().getName()}
                                        </div>
                                    </div>
                                    <div class="col-span-2 flex items-center">
                                        <p class="text-sm font-medium text-black dark:text-white">$${withdrawal.getAmount()}</p>
                                    </div>
                                    <div class="col-span-1 flex items-center">
                                        <p class="inline-flex rounded-full bg-success bg-opacity-10 px-3 py-1 text-sm font-medium text-success">
                                            ${withdrawal.getStatus()}
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
<script>
    function onStatusSelected() {
     const selectElement = document.getElementById("status");

     const selectedOption = selectElement.value;
     window.location = '/admin/withdrawals?status=' + selectedOption;
 }

 window.onload = function () {
     document.getElementById("status").addEventListener("change", onStatusSelected);
     document.getElementById('status').value = '${status}';
 };
</script>
</html>